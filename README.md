【前提と私の背景】
私はシステムエンジニア（6年目・PMP保有）です。これまで要件定義やPython/Docker等での開発経験はありますが、今後は20年稼働しているJava/Tomcat/PostgreSQL環境（オンプレ）の開発グループに配属され、TPMとしてベンダーコントロール・コードレビュー・バグ切り分けを担当します。
Java Silver SE 17の取得を目指しつつ、ブラックボックスを排除してJavaの基礎の動きを完全に理解するため、このチャットでは「フレームワーク（Spring等）やビルドツール（Gradle等）を一切使わないPure Javaでの開発」のサポートをお願いします。

【確定している開発環境・DB管理・Git運用前提】
・OS：Windows 11
・JDK：Eclipse Temurin 17 LTS（D:\java\tools\jdk\jdk-17.0.21.10002-hotspot）をJAVA_HOMEおよびPath（ユーザー環境変数Pathの先頭）に設定済み。古い競合JDKは整理済み。
・PostgreSQL：PostgreSQL 19を D:\java\tools\PostgreSQL\19 にインストール済み（localhost:5432、ユーザー postgres、psql 19.0 での接続確認済み）。管理ツールはpgAdmin 4とpsqlを併用。
・DB管理方針：同一PostgreSQLインスタンス（ポート5432）上で検証・用途ごとにデータベースを分離（例：parking_cli_db、sandbox_db等）して運用し、テーブル定義（DDL）はSQLファイルで管理して再現性を担保する方針。
・ディレクトリ構成：D:\java\dev\eclipse-workspace（Eclipseワークスペース）、D:\java\dev\java-projects\parking-cli（Git管理リポジトリ）
・IDE／エディタ：ビルド・実行・デバッグはEclipse（ワークスペースおよびプロジェクトのコンパイラ準拠レベル17、文字コードUTF-8設定済み）、コード編集・Git操作はVSCode（ソース管理画面・グラフ表示を使用）で連携。
・GitHub運用：GitHub上のparking-cliリポジトリと連携済み。mainを基準としつつ、機能単位でブランチを切って作業・プッシュ・PRマージする現場スタイルの運用を練習中。

【今回のゴール】
コマンドプロンプト（CLI）で動く「駐車場予約ツール」を作成し、JDBCを使ってPostgreSQLと直接通信するプログラムを構築します。

【AI（あなた）への指示】
1. Spring BootやGradleなどのモダンなツールは絶対に使わず、javacとjavaコマンドで動く原始的な手順で解説してください。
2. 私はITの基本概念（DB、ネットワーク等）は理解しているので、専門用語の過度な噛み砕きは不要です。「Javaではどう書くか」「メモリ上でどうデータが渡るか」にフォーカスしてください。
3. まずは、PostgreSQLにJDBCで接続し、CLI上で「1. 登録」「2. 参照」を選ぶメニューを表示する最初のクラス設計（必要な.javaファイルと役割）の提案から始めてください。コードは一気に出力せず、ステップバイステップで進めてください。
