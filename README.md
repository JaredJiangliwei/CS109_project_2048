加油！
2024/5/14：已经有1100多行代码啦
2024/5/15：逐渐完善！
2024/6/4:答辩圆满结束！约1800行代码。既然java A课结束了，那就把我们辛苦写的程序（bugs）公开吧

这是一个用java编写的实现2048游戏的代码。游戏有账号登录，存档功能，计时模式，自定义棋盘模式，音乐音效，“美观”的GUI，AI辅助功能，小道具功能......
关于用户名和密码的信息会储存在users.txt文件中（没有加密功能），存档的话会创建一个D盘//program file//2048的文件夹，然后用户点save时会创建一个在这个文件夹中的文本文档，名字为用户名.txt,load时会在这里读取。
直接将所有这里的文件拖到IDEA，然后在main中运行，应该是可以运行的，第一次如果不行再运行一次，（如果还不行可能是没有users.txt？
因为我们代码涉及到图片，音乐，所以，若直接运行代码时，会报一些找不到对应图片文件的FileNotFoundException,但这个我们都catch了，所以不会太影响正常游戏运行（只是控制台一直出红字），如果要解决这个问题，可以将音频，图片下载一下，然后在代码中导入文件的地方将位置改为自己电脑中这些图片音频储存的位置
至于游戏内部的逻辑的话，额，你们自己看看吧，嘿嘿
最后感谢我的卷王搭子w1n,她勇敢有决心的写了ai，自定义棋盘，计时器棋盘等高级功能，带我躺赢java（bushi

This is the java code implementing the Game 2048. 
There are Login function, Save-Load function, AI, music effect, nice GUI, Time-limited mode, self-choose panel mode, little propts function in the game.
The information about the username and password is stored in users.txt (without encryption) , in terms of save-load, the program will create a folder in D://program files//2048, when a user click "save" , the program will create a txt file in the folder, with its name:username.txt, when user click "load", the program will find data in the txt file
I think you can run the program right away if you drag all the file in this repository into your IDEA, if you can not, maybe try it again, if you can not again, maybe you should create users.txt, 
Because there are music and picture involved in the program, if you run the program right away, you will probably encounter FileNotFoundException, if you want to fix it , you should download the music and picture as well, and put the location of your music and picture in the proper location of the code.
As for the logic of the game, emmm, you will read yourself, haha
In the end, I thank my partner very much. Actually I think she contributes more than me. 
