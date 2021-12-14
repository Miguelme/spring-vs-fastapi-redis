from urllib.parse import quote_plus

import sqlalchemy as sqlalchemy


class RegistryService:
    MYSQL_CONNECTOR_BASE_PATH = "mysql+pymysql://{0}:{1}@{2}/{3}"
    TIMEOUT_IN_SECONDS = 5

    def __init__(self):
        user = "user"
        password = "password"
        host = "db:3306"
        db_name = "db"
        self.repository_engine = sqlalchemy.create_engine(
            RegistryService.MYSQL_CONNECTOR_BASE_PATH.format(
                quote_plus(user), quote_plus(password), host, db_name
            ),
            connect_args={"connect_timeout": RegistryService.TIMEOUT_IN_SECONDS},
        )

    def get_features(self):
        with self.repository_engine.connect() as con:
            value = con.execute("SELECT * FROM test")
            print(f"value {value}")
        return [v for v in value]
