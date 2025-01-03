import httpx
from tqdm import tqdm
import json

#TODO: Replace with your own token
AUTHORIZATION = ""

def upload_data(data_list):
    res = httpx.post(
        "http://127.0.0.1:8080/api/character/batchAdd",
        json=data_list,
        headers={
            "Authorization": AUTHORIZATION,
            "content-type": "application/json"
        },
        timeout=None
    )
    return res.json()


data_list = []
with open("./result/log3.jsonl", "r", encoding="utf-8") as f:
    for line in tqdm(f.readlines()):
        data = json.loads(line)
        data_list.append(data)

    result = upload_data({"batchData": data_list})
    print(result)
