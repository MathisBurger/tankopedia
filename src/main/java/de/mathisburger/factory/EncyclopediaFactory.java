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

        // Clear database entries
        this.removeAllTankModules();
        this.removeAllTanks();

        // Add new entries
        this.addTankModules(modules);
        this.addTanks(tanks);
        this.addNextTanks(tanks);
        this.addLastTanks(tanks);
        this.addCrewMembers(tanks);
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

        // Establish relations
        this.addTankModuleRelations(modules);
        this.addTankModuleSpecialRelations(tanks);
        System.out.println("Import done!");
    }

    @Transactional
    public void removeAllTankModules() {
        List<TankModule> modules = this.tankModuleRepository.findAll().list();
        for (TankModule module : modules) {
            this.entityManager.remove(module);
        }
    }

    @Transactional
    public void removeAllTanks() {
        List<Tank> tanks = this.tankRepository.findAll().list();
        for (Tank tank : tanks) {
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
