# Noah(开发中)

> 我的毕业设计项目，一个博客，没有技术可言（暂时），只为了将项目功能实现.

## 简介

**Noah**`[ˈnəuə]`,意为“诺亚”，含义“幸存者”，“救世主”,也是我的英文名

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

## Bug

|  序号   |            路径             |         位置         |                  描述                  |                    原因                    | 优先级 |                    解决办法                    |
|:------:|:---------------------------:|:-------------------:|:--------------------------------------:|:-----------------------------------------:|:-----:|:---------------------------------------------:|
| ~~1~~  | ~~/admin/blogs/{id}/input~~ | ~~blog-input.html~~ |         ~~博客分类默认全部选中~~          |         ~~thymeleaf模块处理不完善~~         | ~~3~~ |                自己写js逻辑判断                 |
|   2    |        /admin/blogs         |     blogs.html      |    点击多个文章设置后单选框值初始化不显示    |                   未知                    |   3   |                                               |
| ~~3~~  |      ~~/admin/blogs~~       |   ~~blogs.html~~    | ~~点击设置按钮后带有popup的按钮点击都失效~~ |              ~~可能为控件绑定失效~~              |   ~~3~~   |                            |
| ~~4~~  |      ~~/admin/blogs~~       |   ~~blogs.html~~    |   ~~局部刷新导致popup的按钮点击都失效~~    |            ~~可能为局部刷新问题~~            | ~~3~~ | 不使用thymeleaf script模块实现，引申出下面一个bug |
|   5    |        /admin/blogs         |     blogs.html      |          popup需点击两次才能显示          |  先触发标签的点击事件才能处理popup的点击事件   |   3   |                                               |
|   6    |      /admin/blog/input      |   blog-input.html   |     分类列表的新增按钮改为当前页面新增      | 当时暂未想到好的办法，现在已经可以实现，后续优化 |   1   |                                               |
|   7    |   /admin/blogs/{id}/input   |   blog-input.html   |           博客分类默认全部选中            |       找到另外解决办法可以实现，后续优化       |   1   |                                               |
|   8    |  /admin/blogs/{id}/setting  |     blogs.html      |        设置页面提交信息不是局部更新        |              暂未能找到解决办法              |   3   |                                               |
|   9    |        /admin/blogs/        |     blogs.html      |  设置按钮通过API获取设置信息来初始化默认值   |            理论可以实现，后续优化            |   1   |                                               |
|   10   |     /admin/blogs/input      |  blogs-input.html   |           设置form没有非空判断           |            理论可以实现，后续优化             |   1   |                                               |
|   11   |        /admin/blogs/        |     blogs.html      |           设置form没有非空判断           |            理论可以实现，后续优化             |   1   |                                               |
| ~~12~~ |      ~~/admin/blogs/~~      |   ~~blogs.html~~    |   ~~thymeleaf js 后台数据调试直接显示~~   |        ~~暂未想到解决办法，后续优化~~         | ~~3~~ |            移除thymeleaf script模块            |
| ~~13~~ |      ~~/admin/blogs~~       |   ~~blogs.html~~    |  ~~回收站的提示框位置显示不能在按钮上方~~   |  ~~自己技术不行，也不知道怎么调整，暂且搁置~~   | ~~2~~ |                调试出了显示办法                 |
|   14   |        /admin/blogs         |     blogs.html      |           标签默认值无法初始化            |     value赋值了，但无法动态生成,暂且搁置      |   3    |                                              |
|   ~~15~~   |                             |   ~~_fragments.html~~   |      ~~菜单栏-个人资料下方多出来一点空白~~      | ~~应该是SemanticUi中的问题~~  |   ~~10~~   |  ~~已经放弃，尝试了但没有解决~~ |
|   16   |      /admin/categories      |   categories.html   |       删除分类后上级目录没法显示选项       |                  局部刷新                   |   3   |                                               |
|   17   |      /admin/blogs      |   blogs.html   |       列表局部刷新       |                  使用的默认get方法                   |   1   |                     在写一个局部刷新方法                          |
|   18   |      /admin/comments      |   comments.html   |       列表局部刷新       |                  使用的默认get方法                   |   1   |                     在写一个局部刷新方法                          |

## 学习交流

<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/QQ.png" width="20%" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/WeChat.png" width="20%"/>

## 赞助

微信扫一扫,请作者喝一杯咖啡?

<img src="https://raw.githubusercontent.com/wuGuangLei/ArticalBed/main/Public/Reward.png" width="30%"/>