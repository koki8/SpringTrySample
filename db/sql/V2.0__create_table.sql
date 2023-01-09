-- ログインユーザー管理用テーブル作成
CREATE TABLE IF NOT EXISTS loginuser(
user_id BIGSERIAL NOT NULL,
user_name VARCHAR(64) NOT NULL,
password VARCHAR(128) NOT NULL,
authority VARCHAR(100) NOT NULL,
PRIMARY KEY(user_id)
);

