# Spear Backport Addon

为 [Backported Spears](https://github.com/Unknowneth/Backported-Spears) 补充多个模组材质的长矛。

> Adds spears in various mod materials to Backported Spears.

- 适用版本：Minecraft 1.21.1 + NeoForge
- Mod ID：`spear_backport_addon`
- 许可证：MIT License

## 内容

- **Oreganized**
  - 银矛
- **BetterEnd (New Dawn)**
  - 塔拉斯金矛、终末合金矛、太古合金矛
  - 塔拉斯金矛头、终末合金矛头、太古合金矛头（锻造台合成材料）
- **Caverns & Chasms**
  - 银矛（物理伤害 + 魔法伤害）
  - 死灵合金矛（攻击施加缓慢）
  - 铜矛（含氧化与涂蜡变体）

## 依赖

| 模组 | 类型 | 说明 |
| --- | --- | --- |
| [Backported Spears](https://github.com/Unknowneth/Backported-Spears) | 必装 | 长矛核心 |
| [Oreganized](https://github.com/TeamGalena/oreganized) | 可选 | 银矛 |
| [BetterEnd (New Dawn)](https://github.com/Reijin2312/BetterEnd-New-Dawn) | 可选 | 三种长矛 + 三种矛头 |
| [Caverns & Chasms](https://github.com/team-abnormals/caverns-and-chasms) | 可选 | 银矛 / 死灵合金矛 / 铜矛 |

> 未安装对应模组时，相关物品不会注册，不影响其余内容。

## 构建

```bash
.\gradlew.bat build
```

产物位于 `build/libs/`。

> `libs/` 内已附带 spear-backport 的 jar（Backported Spears，CC0-1.0），clone 后可直接编译。

## 致谢 / Credits

- 部分长矛贴图修改自 [Backported Spears](https://github.com/Unknowneth/Backported-Spears)（CC0-1.0）
- 材质风格参考 [Oreganized](https://github.com/TeamGalena/oreganized)（MIT License）
- 材质风格参考 [BetterEnd New Dawn](https://github.com/Reijin2312/BetterEnd-New-Dawn)（MIT License，Copyright (c) 2020 paulusGit, 2026 Raijin）

## 许可证

本项目采用 [MIT License](LICENSE)。
