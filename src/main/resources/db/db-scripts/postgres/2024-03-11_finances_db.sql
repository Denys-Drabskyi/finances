CREATE SCHEMA IF NOT EXISTS finances;

/*==============================================================*/
/* TABLE: USER                                                  */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS finances.user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_user_username ON finances.user(username);

/*==============================================================*/
/* TABLE: ACCOUNT                                               */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS finances.account (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    currency VARCHAR(3) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES finances.user(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_account_user_id ON finances.account(user_id);

/*==============================================================*/
/* TABLE: TRANSACTION                                           */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS finances.transaction (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMP NOT NULL DEFAULT NOW(),
    description TEXT,
    FOREIGN KEY (account_id) REFERENCES finances.account(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_transaction_account_id ON finances.transaction(account_id);
