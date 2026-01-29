--
CREATE TABLE Users (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(20) NOT NULL,
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ChatGroup (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    ownerId         BIGINT NOT NULL,
    name            VARCHAR(100) NOT NULL,
    description     VARCHAR(255),
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ChatGroupUser (
    groupId         BIGINT NOT NULL,
    userId          BIGINT NOT NULL,
    role            VARCHAR(20),
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    leftAt          TIMESTAMP,
    PRIMARY KEY (groupId, userId)
);

CREATE TABLE ChatChannel (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    groupId         BIGINT NOT NULL,
    name            VARCHAR(100) NOT NULL,
    type            VARCHAR(20),    -- PUBLIC, PRIVATE
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (groupId, name)
);

-- PUBLIC 채널은 생략, PRIVATE 채널인 경우에만 사용
CREATE TABLE ChatChannelUser (
    channelId       BIGINT NOT NULL,
    userId          BIGINT NOT NULL,
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    leftAt          TIMESTAMP,
    PRIMARY KEY (channelId, userId)
);

CREATE TABLE ChatChannelUserStatus (
    channelId       BIGINT NOT NULL,
    userId          BIGINT NOT NULL,
    lastOffset      BIGINT NOT NULL,
    updatedAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (channelId, userId)
);

CREATE TABLE ChatMessage (
    id              BIGINT PRIMARY KEY,
    channelId       BIGINT NOT NULL,
    userId          BIGINT NOT NULL,
    type            VARCHAR(20),    -- TEXT, EVENT, IMAGE, EMOJI
    content         TEXT,
    optional        TEXT,
    createdAt       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_message_channel_time ON ChatMessage (channelId, createdAt DESC);