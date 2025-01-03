from concurrent.futures import ThreadPoolExecutor
import pandas as pd
from tqdm import tqdm
import json
import httpx


def get_character_detail(character_id: int):
    print(f"Getting detail for id:{character_id}")
    res = httpx.get(f"https://api.bgm.tv/v0/characters/{character_id}")
    return res.json()


def get_character_images(character_id: int):
    data = get_character_detail(character_id)
    large = data["images"]["large"]
    if large is None:
        raise ValueError(f"Character {character_id} has no image")
    images = [large]
    return images


def str_to_list(s, sep=","):
    if s is None:
        return []
    return s.split(sep)


def birthday_convert(year, month, day):
    if year is None or year is pd.NA:
        return f"{month}-{day}"
    else:
        return f"{year}-{month}-{day}"


df = pd.read_csv("./result/character_cleaned.csv")
df["year"] = df["year"].astype("Int64")
df["month"] = df["month"].astype("Int64")
df["day"] = df["day"].astype("Int64")
df["other_names"] = df["other_names"].astype(str)


data_list = []

for idx, row in tqdm(df.iterrows(), total=df.__len__()):
    data = {
        "name": row["name_cn"],
        "originName": row["name"],
        "gender": row["gender"],
        "otherName": str_to_list(row["other_names"]),
        "birthday": birthday_convert(row["year"], row["month"], row["day"]),
        "bangumiId": row["id"],
    }
    data_list.append(data)

with open("./result/log.json", "w", encoding="utf-8") as f:
    f.write(json.dumps(data_list, ensure_ascii=False, indent=4))

task = []


def get_character_images_task(data, f):
    print(f"Getting images for id:{data['bangumiId']} name: {data['name']}")
    character_id = data["bangumiId"]
    images = get_character_images(character_id)
    data["images"] = images
    print(f"Got images for id:{data['bangumiId']} name: {data['name']} done")
    # data.remove("bangumiId")
    f.write(json.dumps(data, ensure_ascii=False) + "\n")
    return data


with open("./log3.jsonl", "w", encoding="utf-8") as f:
    with ThreadPoolExecutor(max_workers=20) as executor:
        for data in data_list:
            #if data["bangumiId"] <= 120000:
            #    continue
            task.append(executor.submit(get_character_images_task, data, f))

        for t in task:
            data = t.result()
        #    f.write(json.dumps(data, ensure_ascii=False) + "\n")

        print("All done")
