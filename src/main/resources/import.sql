create sequence if not exists pomodoro_seq;

create table if not exists pomodoro
(
    id                   bigint primary key,
    pomodoro_duration    integer,
    short_break_duration integer,
    long_break_duration  integer,
    num_of_pomodoros     integer,
    current_number       integer,
    elapsed_time         integer,
    type                 VARCHAR,
    state                VARCHAR
);
