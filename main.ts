import { open } from "node:fs/promises";
import type {  WikiMap } from "@bgm38/wiki";
import { parseToMap } from "@bgm38/wiki";

export interface Character {
  id: number;
  name: string;
  name_cn: string;
  other_names: string[];
  birthday: string;
  gender: string;
}

const filename = "./data/character.jsonlines";

(async () => {
  const file = await open(filename, "r");
  const error_file = await open("./result/error_id.txt", "w");
  const character_file = await open("./result/character.jsonlines", "w");
  let error_id: number[] = [];

  for await (const line of file.readLines()) {
    const data = JSON.parse(line);

    const id = data.id;
    const name = data.name;
    const infobox = data.infobox;
    let wikiMap: WikiMap;
    try {
      wikiMap = parseToMap(infobox);
    } catch (e) {
      error_id.push(id);
      error_file.write(`error id:${id},error: ${e}\n`);
      continue;
    }
    // console.log(wikiMap);
    const name_cn = wikiMap.data.get("简体中文名")?.value;
    const other_names = wikiMap.data
      .get("别名")
      ?.values?.map((value) => {
        if (value.v) return value.v;
      })
      .filter((value) => value !== undefined);
    const birthday = wikiMap.data.get("生日")?.value;
    const gender = wikiMap.data.get("性别")?.value;

    const character: Character = {
      id: id,
      name: name,
      name_cn: name_cn || name,
      other_names: other_names || [],
      birthday: birthday || "",
      gender: gender || "",
    };
    const jsonline = JSON.stringify(character) + "\n";
    character_file.write(jsonline);
  }
  console.log("error_id: ", error_id);
})();
