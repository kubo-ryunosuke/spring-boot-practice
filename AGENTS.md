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
3. **ドメインモデル配置**:
   - `domain` 直下にビジネス用語ベースで配置（例: `Employee`）。
   - Repositoryの**インターフェース**は `domain`、**実装**は `infrastructure` に配置。
   - サブパッケージ（例: `domain.employee`）は、同一集約でグルーピングが必須な場合のみ作成する。

## 整合性と変更のルール
1. **変更の最小化**: 既存の設計を尊重し、不必要な大規模変更は避ける。
2. **不整合の排除 (Clean-up)**:
   - ライブラリ追加や構成変更を行う際は、**それにより不要・矛盾する既存の記述（依存関係、旧方針のテスト、設定）がないか必ず確認する。**
   - 新方針と矛盾する古いコードや設定は「残す」のではなく、積極的に**削除・修正**し、プロジェクト全体を現在の方針に完全適合させる。