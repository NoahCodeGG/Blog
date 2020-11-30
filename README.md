# Noah(开发中)

> 我的毕业设计项目，一个博客，没有技术可言（暂时），只为了将项目功能实现.

## 简介

**Noah**`[ˈnəuə]`,意为“诺亚”，含义“幸存者”，“救世主”.

## 技术依赖

- [Spring Boot](https://github.com/spring-projects/spring-boot)
- [MyBatis](https://github.com/mybatis/mybatis-3)
- [MySql](https://www.mysql.com/)
- [Semantic UI](https://github.com/Semantic-Org/Semantic-UI)
- ……

## 参考资料/项目

- [Halo](https://github.com/halo-dev/halo) | 是一款现代化的个人独立博客系统，给习惯写博客的同学多一个选择。
- [blog_demo](https://github.com/wuGuangLei/blog_demo) | 自己学习时看的博客开发教程
- ……

## 许可证

![tampermonkey](https://img.shields.io/badge/license-Mit-lightgrey)

> Noah 使用 Mit 协议开源，请尽量遵守开源协议。

## 未能实现

| 序号 |       路径        |      位置       |         描述         |          原因           | 优先级 |
| :--: | :---------------: | :-------------: | :------------------: | :---------------------: | :----: |
~~|1|/admin/blogs|blogs.html|回收站的提示框位置显示不能在按钮上方|自己技术不行，也不知道怎么调整，暂且搁置|2|~~

## Bug

| 序号 |       路径        |      位置       |         描述         |          原因           | 优先级 |
| :--: | :---------------: | :-------------: | :------------------: | :---------------------: | :----: |
~~|  1   | /admin/blogs/{id}/input | blog-input.html | 博客分类默认全部选中 | thymeleaf模块处理不完善 |   3    |~~
|2|/admin/blogs|blogs.html|点击多个文章设置后单选框值初始化不显示|未知|3|
|3|/admin/blogs|blogs.html|标签默认值无法初始化|value赋值了，但无法动态生成|3|

## 优化

| 序号 |    路径     |      位置       |                描述                |                  原因                  | 优先级 |
| :--: | :---------: | :-------------: | :--------------------------------: | :------------------------------------: | :----: |
|  1   | /admin/blog/input | blog-input.html | 分类列表的新增按钮改为当前页面新增 | 当时暂未想到好的办法，现在已经可以实现 |   3    |
|  2   | /admin/blogs/{id}/input | blog-input.html | 博客分类默认全部选中 | 找到另外解决办法可以实现 |   3    |

## 学习交流
<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/QQ.png" width="20%" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/WeChat.png" width="20%"/>

## 赞助

微信扫一扫,请作者喝一杯咖啡?

<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/Reward.png" width="30%"/>