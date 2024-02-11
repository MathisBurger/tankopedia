package de.mathisburger.factory;

import de.mathisburger.api.WotApiClient;
import de.mathisburger.api.models.datatypes.TankData;
import de.mathisburger.api.models.results.TanksResult;
import de.mathisburger.api.models.subtypes.CrewMemberData;
import de.mathisburger.api.models.subtypes.TankModuleData;
import de.mathisburger.config.WargamingConfig;
import de.mathisburger.entity.*;
import de.mathisburger.repository.TankModuleRepository;
import de.mathisburger.repository.TankRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EncyclopediaFactory {

    @Inject
    WargamingConfig config;

    @Inject
    @RestClient
    WotApiClient apiClient;

    @Inject
    EntityManager entityManager;

    @Inject
    TankModuleRepository tankModuleRepository;

    @Inject
    TankRepository tankRepository;

    @Scheduled(cron = "{wargaming.schedule}")
    void reloadEncyclopedia() {
        this.removeAllTankData();
        this.reloadTanks();
        System.out.println("Imported tanks");
        // TODO: Add default profile
        // TODO: Implement consumables with relation to tanks
        // TODO: Implement accievements
        // TODO: Implement maps
        // TODO: Implement personal missions
        // TODO: Implement personal reserves
        // TODO: Implement modules
        // TODO: Implement badges
        // TODO: Implement crew qualifications
        // TODO: Implement crew skills

        // TODO: Remove all static entities as operations

        System.out.println("Import done!");
    }

    private void reloadTanks() {
        List<TankData> tanks = new ArrayList<>();
        TanksResult result = null;
        int pageNo = 1;
        while ((result = apiClient.tanks(config.applicationID(), pageNo)) != null && result.data != null && result.data.isPresent()) {
            tanks.addAll(result.data.get().values());
            pageNo++;
        }

        List<TankModuleData> modules = new ArrayList<>();
        for (TankData tank : tanks) {
            modules.addAll(tank.modules_tree.values());
        }
        // Add new entries
        this.addTankModules(modules);
        this.addTanks(tanks);
        this.addNextTanks(tanks);
        this.addLastTanks(tanks);
        this.addCrewMembers(tanks);
        this.addDefaultProfiles(tanks);

        // Add relations
        this.addTankModuleRelations(modules);
        this.addTankModuleSpecialRelations(tanks);
    }

    @Transactional
    public void removeAllTankData() {
        // Remove tank modules
        for (TankModule module : this.tankModuleRepository.findAll().list()) {
            this.entityManager.remove(module);
        }
        // Remove tanks
        for (Tank tank : this.tankRepository.findAll().list()) {
            this.entityManager.remove(tank);
        }
    }

    @Transactional
    public void addTankModules(List<TankModuleData> modules) {
        for (TankModuleData module : modules) {
            if (this.tankModuleRepository.findById(module.module_id) == null) {
                TankModule mod = new TankModule();
                mod.id = module.module_id;
                mod.isDefault = module.is_default;
                mod.priceXp = module.price_xp;
                mod.priceCredit = module.price_credit;
                mod.type = module.type;
                mod.nextModules = new ArrayList<>();
                mod.nextTanks = new ArrayList<>();
                mod.previousModules = new ArrayList<>();
                mod.tankRadios = new ArrayList<>();
                mod.tankGuns = new ArrayList<>();
                mod.tankSuspensions = new ArrayList<>();
                mod.tankEngines = new ArrayList<>();
                mod.tankTurrets = new ArrayList<>();
                this.entityManager.persist(mod);
            }
        }
    }

    @Transactional
    public void addTanks(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = new Tank();
            tank.id = tankData.tank_id;
            tank.isWheeled = tankData.is_wheeled;
            tank.isPremium = tankData.is_premium;
            tank.tag = tankData.tag;
            tank.smallIcon = tankData.images.small_icon;
            tank.contourIcon = tankData.images.contour_icon;
            tank.bigIcon = tankData.images.big_icon;
            tank.type = tankData.type;
            tank.description = tankData.description;
            tank.shortName = tankData.short_name;
            tank.isPremiumIgr = tankData.is_premium_igr;
            tank.nation = tankData.nation;
            tank.tier = tankData.tier;
            tank.isGift = tankData.is_gift;
            tank.name = tankData.name;
            if (tankData.price_gold != null && tankData.price_gold.isPresent()) {
                tank.priceGold = tankData.price_gold.get();
            }
            if (tankData.price_credit != null && tankData.price_credit.isPresent()) {
                tank.priceCredit = tankData.price_credit.get();
            }
            tank.previousModules = new ArrayList<>();
            tank.radios = new ArrayList<>();
            tank.suspensions = new ArrayList<>();
            tank.engines = new ArrayList<>();
            tank.guns = new ArrayList<>();
            tank.turrets = new ArrayList<>();
            this.entityManager.persist(tank);
        }
    }

    @Transactional
    public void addNextTanks(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = this.tankRepository.findById(tankData.tank_id);
            if (tankData.next_tanks != null) {
                for (String strNextId : tankData.next_tanks.keySet()) {
                    int nextId = Integer.parseInt(strNextId);
                    Tank nextTank = this.tankRepository.findById(nextId);
                    NextTank obj = new NextTank();
                    obj.nextTank = nextTank;
                    obj.priceXp = tankData.next_tanks.get(strNextId);
                    this.entityManager.persist(obj);
                    tank.nextTanks.add(obj);
                }
                this.entityManager.persist(tank);
            }
        }
    }

    @Transactional
    public void addLastTanks(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = this.tankRepository.findById(tankData.tank_id);
            if (tankData.prices_xp != null) {
                for (String strLastId : tankData.prices_xp.keySet()) {
                    int lastId = Integer.parseInt(strLastId);
                    Tank lastTank = this.tankRepository.findById(lastId);
                    LastTank obj = new LastTank();
                    obj.lastTank = lastTank;
                    obj.costXp = tankData.prices_xp.get(strLastId);
                    this.entityManager.persist(obj);
                    tank.lastTanks.add(obj);
                }
                this.entityManager.persist(tank);
            }
        }
    }

    @Transactional
    public void addCrewMembers(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = this.tankRepository.findById(tankData.tank_id);
            for (CrewMemberData memberData : tankData.crew) {
                CrewMember member = new CrewMember();
                member.memberId = memberData.member_id;
                member.roles = memberData.roles.keySet();
                this.entityManager.persist(member);
                tank.crewMembers.add(member);
            }
            this.entityManager.persist(tank);
        }
    }

    @Transactional
    public void addDefaultProfiles(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = this.tankRepository.findById(tankData.tank_id);
            DefaultProfile defaultProfile = new DefaultProfile();
            defaultProfile.maxAmmo = tankData.default_profile.max_ammo;
            defaultProfile.weight = tankData.default_profile.weight;
            defaultProfile.hp = tankData.default_profile.hp;
            defaultProfile.hullWeight = tankData.default_profile.hull_weight;
            defaultProfile.speedForward = tankData.default_profile.speed_forward;
            defaultProfile.hullHp = tankData.default_profile.hull_hp;
            defaultProfile.speedBackward = tankData.default_profile.speed_backward;
            defaultProfile.maxWeight = tankData.default_profile.max_weight;

            DefaultProfileModules modules = new DefaultProfileModules();
            modules.gunId = tankData.default_profile.modules.gun_id;
            modules.suspensionId = tankData.default_profile.modules.suspension_id;
            modules.radioId = tankData.default_profile.modules.radio_id;
            modules.engineId = tankData.default_profile.modules.engine_id;
            if (tankData.default_profile.modules.turret_id != null && tankData.default_profile.modules.turret_id.isPresent()) {
                modules.turretId = tankData.default_profile.modules.turret_id.get();
            }
            this.entityManager.persist(modules);
            defaultProfile.modules = modules;

            DefaultProfileGun gun = new DefaultProfileGun();
            gun.moveDownArc = tankData.default_profile.gun.move_down_arc;
            gun.caliber = tankData.default_profile.gun.caliber;
            gun.name = tankData.default_profile.gun.name;
            gun.weight = tankData.default_profile.gun.weight;
            gun.moveUpArc = tankData.default_profile.gun.move_up_arc;
            gun.fireRate = tankData.default_profile.gun.fire_rate;
            gun.dispersion = tankData.default_profile.gun.dispersion;
            gun.tag = tankData.default_profile.gun.tag;
            gun.traverseSpeed = tankData.default_profile.gun.traverse_speed;
            gun.reloadTime = tankData.default_profile.gun.reload_time;
            gun.tier = tankData.default_profile.gun.tier;
            gun.aimTime = tankData.default_profile.gun.aim_time;
            this.entityManager.persist(gun);
            defaultProfile.gun = gun;

            if (tankData.default_profile.turret != null) {
                DefaultProfileTurret turret = new DefaultProfileTurret();
                turret.name = tankData.default_profile.turret.name;
                turret.weight = tankData.default_profile.turret.weight;
                turret.viewRange = tankData.default_profile.turret.view_range;
                turret.hp = tankData.default_profile.turret.hp;
                turret.tag = tankData.default_profile.turret.tag;
                turret.traverseRightArc = tankData.default_profile.turret.traverse_right_arc;
                turret.traverseLeftArc = tankData.default_profile.turret.traverse_left_arc;
                turret.tier = tankData.default_profile.turret.tier;
                if (tankData.default_profile.turret.traverse_speed != null && tankData.default_profile.turret.traverse_speed.isPresent()) {
                    turret.traverseSpeed = tankData.default_profile.turret.traverse_speed.get();
                }
                this.entityManager.persist(turret);
                defaultProfile.turret = turret;
            }

            DefaultProfileRadio radio = new DefaultProfileRadio();
            radio.tier = tankData.default_profile.radio.tier;
            radio.signalRange = tankData.default_profile.radio.signal_range;
            radio.tag = tankData.default_profile.radio.tag;
            radio.name = tankData.default_profile.radio.name;
            radio.weight = tankData.default_profile.radio.weight;
            this.entityManager.persist(radio);
            defaultProfile.radio = radio;

            DefaultProfileAmmo ammo = new DefaultProfileAmmo();
            ammo.penetration = tankData.default_profile.ammo.penetration;
            ammo.type = tankData.default_profile.ammo.type;
            ammo.damage = tankData.default_profile.ammo.damage;
            if (tankData.default_profile.ammo.stun != null && tankData.default_profile.ammo.stun.isPresent()) {
                DefaultProfileStun stun = new DefaultProfileStun();
                stun.duration = tankData.default_profile.ammo.stun.get().duration;
                this.entityManager.persist(stun);
                ammo.stun = stun;
            }
            this.entityManager.persist(ammo);
            defaultProfile.ammo = ammo;

            this.entityManager.persist(defaultProfile);
            tank.defaultProfile = defaultProfile;
            this.entityManager.persist(tank);
        }
    }

    @Transactional
    public void addTankModuleRelations(List<TankModuleData> modules) {
        for (TankModuleData module : modules) {
            TankModule mod = this.tankModuleRepository.findById(module.module_id);
            mod.nextModules = new ArrayList<>();
            mod.nextTanks = new ArrayList<>();
            this.entityManager.persist(mod);
            if (module.next_modules != null && module.next_modules.isPresent()) {
                for (int nextModule : module.next_modules.get()) {
                    TankModule tankModule = this.tankModuleRepository.findById(nextModule);
                    mod.nextModules.add(tankModule);
                    tankModule.previousModules.add(mod);
                    this.entityManager.persist(tankModule);
                }
            }
            if (module.next_tanks != null && module.next_tanks.isPresent()) {
                for (int nextTankId : module.next_tanks.get()) {
                    Tank nextTank = this.tankRepository.findById(nextTankId);
                    mod.nextTanks.add(nextTank);
                    nextTank.previousModules.add(mod);
                    this.entityManager.persist(nextTank);
                }
            }
            this.entityManager.persist(mod);
        }
    }

    @Transactional
    public void addTankModuleSpecialRelations(List<TankData> tanks) {
        for (TankData tankData : tanks) {
            Tank tank = this.tankRepository.findById(tankData.tank_id);
            for (int radioId : tankData.radios) {
                TankModule module = this.tankModuleRepository.findById(radioId);
                tank.radios.add(module);
                module.tankRadios.add(tank);

                this.entityManager.persist(module);
                this.entityManager.persist(tank);
            }
            for (int suspensionId : tankData.suspensions) {
                TankModule module = this.tankModuleRepository.findById(suspensionId);
                tank.suspensions.add(module);
                module.tankSuspensions.add(tank);

                this.entityManager.persist(module);
                this.entityManager.persist(tank);
            }
            for (int engineId : tankData.engines) {
                TankModule module = this.tankModuleRepository.findById(engineId);
                tank.engines.add(module);
                module.tankEngines.add(tank);

                this.entityManager.persist(module);
                this.entityManager.persist(tank);
            }
            for (int gunId : tankData.guns) {
                TankModule module = this.tankModuleRepository.findById(gunId);
                tank.guns.add(module);
                module.tankGuns.add(tank);

                this.entityManager.persist(module);
                this.entityManager.persist(tank);
            }
            for (int turretId : tankData.turrets) {
                TankModule module = this.tankModuleRepository.findById(turretId);
                tank.turrets.add(module);
                module.tankTurrets.add(tank);

                this.entityManager.persist(module);
                this.entityManager.persist(tank);
            }
        }
    }
}
