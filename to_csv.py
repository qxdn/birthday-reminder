"""
read the character extract from dump data
"""

import json
import httpx
import pandas as pd
from tqdm import tqdm


def get_character_detail(character_id: int):
    res = httpx.get(f"https://api.bgm.tv/v0/characters/{character_id}")
    return res.json()


def list_to_str(l, sep=","):
    return sep.join(l)


with open("./result/character.csv", "w", encoding="utf-8") as csv:
    csv.write("id,name,name_cn,other_names,birthday,gender\n")
    with open("./result/character.jsonlines", "r", encoding="utf-8") as f:
        for line in tqdm(f.readlines()):
            data = json.loads(line)
            csv.write(
                f'{data["id"]},"{data["name"]}","{data["name_cn"]}","{list_to_str(data["other_names"])}","{data["birthday"]}","{data["gender"]}"\n'
            )
