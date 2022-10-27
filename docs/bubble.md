快速创建美观的iOS风格的`Dialog`加载对话框

<img src="https://i.loli.net/2021/08/14/8eDp7Oz2CQT9Jcq.gif" width="250"/>

```kotlin
BubbleDialog(this).show()
```

指定标题

```kotlin
BubbleDialog(this, "正在清除中").show()
```

通过覆写`strings`也可以修改默认的标题

```kotlin
<string name="bubble_loading_title">请稍等...</string>
```


## 自动化

如果你想网络请求或异步任务自动显示和隐藏对话框请使用[Net](https://github.com/liangjingkanji/Net)网络请求库(Android上最好的网络请求框架), 这里介绍下简单的使用

### 1) 全局设置

所有默认的`scopeDialog`都会使用全局设置的对话框

```kotlin
NetConfig.initialize("https://github.com/liangjingkanji/Net/", this) {
        setDialogFactory { // 全局加载对话框
            ProgressDialog(it).apply {
                setMessage("我是全局自定义的加载对话框...")
            }
        }
}
```

### 2) 使用

```kotlin
scopeDialog {
    tv_fragment.text = Post<String>("dialog") {
        param("u_name", "drake") // 请求参数
        param("pwd", "123456")
    }.await()
}
```

### 3) 单例设置

如果某个任务需要单独的Dialog可以创建作为参数传入

```kotlin
val dialog = BubbleDialog(context, "清除缓存中")
scopeDialog(dialog) {
    tv_fragment.text = Post<String>("dialog") {
        param("u_name", "drake") // 请求参数
        param("pwd", "123456")
    }.await()
}
```

