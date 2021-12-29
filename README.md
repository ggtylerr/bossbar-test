# Bossbar Test Plugin
This is a quick plugin for 1.18 Spigot, made for MC Events.

## Usage

First create your bar...

`/bbcreate <name> <text>`

...then customize it to your liking.

* `/bbtext <name> [text]` - Text
* `/bbcolor <name> [color]` - Color
* `/bbprogress <name> [progress]` - Progress (0.0 -> 1.0)
* `/bbsegments <name> [segments]` - Segments (0, 6, 10, 12, 20)
* `/bbflags <name>` / `/bbaflags <name> <flag/s>` / `/bbrflags <name> <flag/s>` - List/Add/Remove Flags (Fog, Dark, Music, All)
* `/bbplayers <name>` / `/bbaplayers <name> <player/s>` / `/bbrplayers <name> <player/s>` - List/Add/Remove Players
* `/bbshow <name>` / `/bbhide <name>` / `/bbtoggle <name>` - Show/hide bar

You can also do various stuff to bars:

* `/bblist` - Lists all bars (from this plugin)
* `/bbcopy <name> <newname>` - CTRL+C's that bar
* `/bbcut <name> <newname>` - CTRL+X's that bar
* `/bbdelete <name/s>` - If you can't handle the bar/s

## Building

Build like any other spigot plugin on maven.

## License

This project is licensed under the 
*Do What The F\*ck You Want To Public License* 
(WTFPL),
which lets you,
you guessed it,
<span style="text-decoration: underline">
do what the **f\*ck** you want to.
</span>