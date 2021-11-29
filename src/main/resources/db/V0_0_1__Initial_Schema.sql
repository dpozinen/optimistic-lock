create table answer_option (
    id varchar(36) not null,
    version integer not null,
    primary key (id)
) engine=InnoDB;

create table answer_option_pair (
    id varchar(36) not null,
    version integer not null,
    a_id varchar(36),
    b_id varchar(36),
    primary key (id)
) engine=InnoDB;

create table game (
    id varchar(36) not null,
    version integer not null,
    business_key varchar(150),
    type varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table game_questions (
    game_id varchar(36) not null,
    questions_id varchar(36) not null,
    sort_index integer not null,
    primary key (game_id, sort_index)
) engine=InnoDB;

create table image_single_choice_question (
    id varchar(36) not null,
    valid_answer_id varchar(36),
    primary key (id)
) engine=InnoDB;


create table multi_choice_question (
    id varchar(36) not null,
    primary key (id)
) engine=InnoDB;

create table multi_choice_question_valid_answers (
    multi_choice_question_id varchar(36) not null,
    valid_answers_id varchar(36) not null
) engine=InnoDB;

create table question (
    id varchar(36) not null,
    version integer not null,
    primary key (id)
) engine=InnoDB;

create table question_answer_options (
    question_id varchar(36) not null,
    answer_options_id varchar(36) not null,
    sort_index integer not null,
    primary key (question_id, sort_index)
) engine=InnoDB;

alter table game
    add constraint UK_a8xxp7xgjwpa0iiuo3q4iwj20 unique (business_key);

alter table game_questions
    add constraint UK_ffolpiu4rku24qxgd2mv9rekg unique (questions_id);

alter table multi_choice_question_valid_answers
    add constraint UK_ixyq4gb2ou8o4l7yfj3qq9w1h unique (valid_answers_id);

alter table question_answer_options
    add constraint UK_r7s295xo66hda9din7s3ua5me unique (answer_options_id);

alter table answer_option_pair
    add constraint FK5uvn0d4lvrr0ir9re0glwkljk
        foreign key (a_id)
            references answer_option (id);

alter table answer_option_pair
    add constraint FKsej7vk5pyi890si5ikubr4ivx
        foreign key (b_id)
            references answer_option (id);

alter table game_questions
    add constraint FKfhk918kpau0jdtnudidrnu3ii
        foreign key (questions_id)
            references question (id);

alter table game_questions
    add constraint FKm529s8o0t95ycgtjxvbja5iby
        foreign key (game_id)
            references game (id);

alter table multi_choice_question
    add constraint FK8ih5kejtinwyybbbi4f5562eg
        foreign key (id)
            references question (id);

alter table multi_choice_question_valid_answers
    add constraint FKpdrlcjyucqkv5ihx7vo2fhc0m
        foreign key (valid_answers_id)
            references answer_option (id);

alter table multi_choice_question_valid_answers
    add constraint FKp6ir3kl1bfionmv0su9bnwnx6
        foreign key (multi_choice_question_id)
            references multi_choice_question (id);

alter table question_answer_options
    add constraint FK74oft9q4dc98s6j70ne858moj
        foreign key (answer_options_id)
            references answer_option (id);

alter table question_answer_options
    add constraint FKs7pnwi1hcmsu32rk37cl3dclm
        foreign key (question_id)
            references question (id);

alter table image_single_choice_question
    add constraint FKguwgxqu2g50pn3lbewrs3ytx8
        foreign key (valid_answer_id)
            references answer_option (id);

alter table image_single_choice_question
    add constraint FKqc3ou7ebr304rqwekeix7tyud
        foreign key (id)
            references question (id);
