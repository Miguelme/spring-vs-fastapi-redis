from typing import List

from fastapi import FastAPI

from redis_service.redis_service import RedisService
from random import choice
from string import ascii_lowercase

app = FastAPI()
redis_service = RedisService()


def get_random_string(size):
    return ''.join([choice(ascii_lowercase) for _ in range(size)])


@app.get("/generate/{amount}")
async def generate_key_values(amount: int):
    key_values = {}
    for _ in range(amount):
        random_key = get_random_string(32)
        random_value = get_random_string(64)
        key_values["template:" + random_key] = random_value
    redis_service.save_keys(key_values)
    return True


@app.get("/keys")
async def get_keys():
    return redis_service.get_all_keys()


@app.post("/keys")
async def get_keys_array(keys: List[str]):
    return redis_service.get_keys(keys)

@app.get("/keys/{key}")
async def get_key(key):
    return redis_service.get_key(key)


