# Codex 作業指示書

## プロジェクト概要
Docker Compose ベースの Spring Boot + PostgreSQL 練習プロジェクト。

## 技術スタック
- **Backend**: Spring Boot 4.0.0 (Java 21)
  - Starters: Web, Data JPA, Lombok, Devtools
  - DDL auto: `update`
- **Database**: PostgreSQL 17
  - Host: `db` (Port: 5432, DB: `mydb`)
  - Creds: user=`user`, pass=`password`
- **Root Package**: `com.example.backend`

## アーキテクチャ設計方針 (Onion Arch + Light DDD)
1. **レイヤー構成**:
   - `presentation`: Web/API層。入出力DTOはここに配置し、Domainエンティティを外部に返さない。
   - `application`: ユースケース層。
   - `domain`: ドメインモデル層。他レイヤーに依存しない。
   - `infrastructure`: 実装層（Repository実装、外部API接続など）。

2. **依存方向**: 外側 → 内側（Domain）のみ許可。

3. **ドメイン層のパッケージ構成（役割ベース）**:
   - クラス単位の冗長なサブパッケージ（例: `domain.Employee.Employee`）や集約単位の分割は行わない。
   - 以下の役割別パッケージにフラットに配置する構成とする：
     - **`domain.model`**: エンティティおよび値オブジェクト（Value Object）を配置。
     - **`domain.repository`**: リポジトリの**インターフェース**のみを配置。
   - ※ リポジトリの**実装クラス**は `infrastructure` 層に配置する（例: `infrastructure.repository.EmployeeRepositoryImpl`）。

## 開発ルールと整合性維持
1. **変更の最小化**: 既存の設計を尊重し、不必要な大規模変更は避ける。
2. **不整合の排除 (Clean-up)**:
   - ライブラリ追加や構成変更を行う際は、**それにより不要・矛盾する既存の記述（依存関係、他の方針用のテスト、設定）がないか必ず確認する。**
   - 新方針と矛盾する不要なコードや設定は「残す」のではなく、積極的に**削除・修正**し、プロジェクト全体を現在の方針に完全適合させる。
3. **情報の正確性と検索**:
   - 実装にあたっては「技術スタック」で指定されたバージョンを厳守すること。
   - 指定バージョンのAPI仕様やベストプラクティスについては、自身の学習データ（推測）に頼らず、**必ず検索機能を使用して公式情報による裏付けを取る**こと。