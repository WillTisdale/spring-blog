USE spring_blog_db;

INSERT INTO heroes (name, main_stat, intelligence, agility, strength, health, physical_attack, magic_attack, armor, magic_defense, dodge, armor_penetration, magic_penetration, vampirism, crit_hit_chance)
VALUES ('Alvanor', 'Intelligence', '14,703', '2,444', '2,969', '248,941', '19,641',	'90,829', '5,854', '24,413', null, null, '22,178', null, null),
       ('Andavari', 'Strength', '2,747', '2,877', '14,639', '815,711', '45,375', '8,241', '22,592', '2,747', null, '23,000', null, null, null),
       ('Arachne', 'Agility', '3,432', '12,772', '2,887', '386,087', '55,948', '49,168', '29,522', '14,051', null, null, '10,650', '%80', null),
       ('Artemis', 'Agility', '2,967', '13,340', '2,962', '222,220', '70,070', '8,901', '15,890', '4,258', null, '33,390', null, null, '8,054');
