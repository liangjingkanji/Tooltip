## Tooltip

<p align="center"><img src="https://i.imgur.com/HTSYi1S.png" width="25%"/></p>

<p align="center"><strong>用于支持各种提示工具</strong></p>

<p align="center"><a href="http://liangjingkanji.github.io/Tooltip/">使用文档</a> | <a href="https://coding-pages-bucket-3558162-8706000-16649-587727-1252757332.cos-website.ap-shanghai.myqcloud.com/">备用访问</a></p>

<p align="center"><img src="https://i.imgur.com/ryI7veS.jpg" width="170"/></p>

<p align="center">
<a href="https://jitpack.io/#liangjingkanji/Tooltip"><img src="https://jitpack.io/v/liangjingkanji/Tooltip.svg"/></a>
<img src="https://img.shields.io/badge/language-kotlin-orange.svg"/>
<img src="https://img.shields.io/badge/license-Apache-blue"/>
<a href="https://jq.qq.com/?_wv=1027&k=vWsXSNBJ"><img src="https://img.shields.io/badge/QQ群-752854893-blue"/></a>
</p>



## 介绍

本库集成和简化常用提醒工具

| 提示         | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| Toast        | 异步线程显示<br />连续弹出吐司不会重叠<br />自定义视图<br />全局等级自定义吐司 |
| BubbleDialog | iOS风格加载对话框                                            |



<br>

## 安装

添加远程仓库根据创建项目的 Android Studio 版本有所不同

Android Studio Arctic Fox以下创建的项目 在项目根目录的 build.gradle 添加仓库

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

Android Studio Arctic Fox以上创建的项目 在项目根目录的 settings.gradle 添加仓库

```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

然后在 module 的 build.gradle 添加依赖框架

```groovy
implementation 'com.github.liangjingkanji:Tooltip:1.2.0'
```

<br>

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

