# WorldGenFeatureFix
## What this mod fixes?
When custom world generation datapack uses minecraft:lake or minecraft:snow_and_freeze placed feature with water, The chunk generator crashes. (See [MC-273228](https://bugs.mojang.com/browse/MC/issues/MC-273228) or [MC-272370](https://bugs.mojang.com/browse/MC/issues/MC-272370) for more details).   
Inspired by [This pull request](https://github.com/PaperMC/Paper/pull/11086), This mod apply mixins to make them work.   
## Disclaimer
However, The PR also mentioned that LakeFeature is deprecated and vanilla has moved away from using it for anything except for lava lakes.
The error for unsafe chunk gets removing here is an intentional safeguard against bad code, such as that in the dead path in LakeFeature when creating water lakes. While it may appear to 'fix' the problem at hand it is not a proper solution and will cause other more serious problems to go undetected. Use at your own risk.