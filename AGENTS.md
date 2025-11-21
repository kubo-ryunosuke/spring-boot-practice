# Codex 作業指示書

## 目的

- `compose.yaml` を基点にした Spring Boot + PostgreSQL 練習プロジェクト。Codex 作業時の前提を簡潔に共有。

## 技術スタック

- Backend
  - Spring Boot 4.0.0 (Java 21, Gradle)
  - スターターは WebMVC / Data JPA、Lombok、Devtools。
  - JPA 自動 DDL は `update`。
- Database
  - PostgreSQL 17 (Docker official `postgres:17-alpine`)。
  - 接続は `jdbc:postgresql://db:5432/mydb`
    - user: `user`
    - password: `password`。
- 実行基盤
  - Docker Compose
    - `backend` ビルド & ホットリロード用 `./gradlew -t classes` と `bootRun` を併走
    - DB 初期化は `database/init` をマウント。
- フロントエンド
  - `compose.yaml` に React 用サービスの雛形あり（コメントアウト）。
  - 現在コード未配置（`frontend` 空）。

## バックエンド設計方針

- オニオンアーキテクチャと軽量 DDD を採用する。
- レイヤ構成は `presentation`（Web/API 層）、`application`（ユースケース層）、`domain`（ドメインモデル層）、`infrastructure`（DB・外部サービス実装層）を基本とする。
- 依存方向は外側レイヤから内側レイヤのみ許可し、`domain` は他レイヤに依存しない。
- Java パッケージは `com.example.backend.presentation`、`com.example.backend.application`、`com.example.backend.domain`、`com.example.backend.infrastructure` をベースに構成する。
- ドメインモデルは `domain` 配下にビジネス用語ベースのクラス名で定義し（例: `com.example.backend.domain.Employee`）、リポジトリのインターフェースは `domain` 層、実装は `infrastructure` 層（例: Spring Data JPA）に配置する。
- `domain` 配下のサブパッケージ（例: `com.example.backend.domain.employee`）は、同一コンテキスト/集約に属するエンティティや値オブジェクトが複数存在し、グルーピングの必要が生じた場合にのみ作成する。それ以外は `domain` 直下にエンティティを配置する。
- API 入出力用 DTO は `presentation` 層に置き、`domain` のエンティティを直接外部に返さない。

## 実行メモ

- ローカル起動
  - `docker compose up --build` で backend:8080 / db:5432。
  - Gradle キャッシュはボリューム `gradle-cache`。
- 直接起動
  - `backend` 直下で `./gradlew bootRun`。
  - DB は上記認証情報で接続。

## 注意事項

- 変更は最小差分を基本としつつ、負債固定化は避ける。
