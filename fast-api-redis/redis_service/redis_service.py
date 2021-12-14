import rediscluster
from rediscluster import RedisClusterException


class RedisService:
    def __init__(self):
        self.redis_storage = None

    def get_redis_storage(self):
        if not self.redis_storage:
            startup_nodes = [
                {
                    "host": self._get_redis_host(),
                    "port": self._get_redis_port(),
                }
            ]
            self.redis_storage = rediscluster.RedisCluster(
                startup_nodes=startup_nodes,
                decode_responses=True,
                skip_full_coverage_check=True,
                socket_keepalive=True,
            )
            self.redis_storage.refresh_table_asap = True
        return self.redis_storage

    def _get_redis_host(self):
        return "redis"

    def _get_redis_port(self):
        return "7000"

    def save_keys(self, key_values):
        redis_storage = self.get_redis_storage()
        redis_storage.mset(key_values)

    def save_key(self, key_value):
        redis_storage = self.get_redis_storage()
        redis_storage.set(key_value[0], key_value[1])

    def get_key(self, key):
        redis_storage = self.get_redis_storage()
        return redis_storage.get(key)

    def get_all_keys(self):
        redis_storage = self.get_redis_storage()
        return redis_storage.scan_iter(match="template:*")

    def get_keys(self, keys):
        redis_storage = self.get_redis_storage()
        return redis_storage.mget(keys)
