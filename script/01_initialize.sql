-- DB作成
CREATE DATABASE library;

-- 作成したDBへ切り替え
\c library

-- スキーマ作成
CREATE SCHEMA libraryschema;

-- ロールの作成
CREATE ROLE libraryuser WITH LOGIN PASSWORD 'password';

-- 権限追加
GRANT ALL PRIVILEGES ON SCHEMA libraryschema TO libraryuser;