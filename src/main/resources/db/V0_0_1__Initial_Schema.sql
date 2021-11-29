CREATE TABLE parent (
    id      VARCHAR(36) NOT NULL
        PRIMARY KEY,
    version INT         NOT NULL
) ENGINE = InnoDB;

CREATE TABLE child (
    id                            VARCHAR(36) NOT NULL PRIMARY KEY,
    version                       INT         NOT NULL,
    field_just_for_the_sake_of_it VARCHAR(36)
) ENGINE = InnoDB;

CREATE TABLE parent_child (
    parent_id  VARCHAR(36) NOT NULL,
    child_id   VARCHAR(36) NOT NULL,
#     PRIMARY KEY (parent_id),
    CONSTRAINT uk_ffalpiu4rku24qxgd2mv9rekg
        UNIQUE (child_id),
    CONSTRAINT fkfhl918kpau0jdtnudidrnu3ii
        FOREIGN KEY (child_id) REFERENCES child (id),
    CONSTRAINT fkm329s8o0t95ycgtjxvbja5iby
        FOREIGN KEY (parent_id) REFERENCES parent (id)
) ENGINE = InnoDB;

CREATE TABLE nested_child (
    id                            VARCHAR(36) NOT NULL PRIMARY KEY,
    version                       INT         NOT NULL,
    field_just_for_the_sake_of_it VARCHAR(36)
) ENGINE = InnoDB;

CREATE TABLE child_nested_child (
    child_id  VARCHAR(36) NOT NULL,
    nested_child_id   VARCHAR(36) NOT NULL,
#     PRIMARY KEY (child_id),
    CONSTRAINT uk_ffolpiu4rku24qxgd2mv9rekg
        UNIQUE (nested_child_id),
    CONSTRAINT fkfhk918kpau0jdtnudidrnu3ii
        FOREIGN KEY (nested_child_id) REFERENCES nested_child (id),
    CONSTRAINT fkm529s8o0t95ycgtjxvbja5iby
        FOREIGN KEY (child_id) REFERENCES child (id)
) ENGINE = InnoDB;

# DROP TABLE IF EXISTS flyway_schema_history;
#
# DROP TABLE IF EXISTS  child_nested_child;
#
# DROP TABLE IF EXISTS  parent_child;
#
# DROP TABLE IF EXISTS  child;
#
# DROP TABLE IF EXISTS  parent;
#
# DROP TABLE IF EXISTS  nested_child;
#
