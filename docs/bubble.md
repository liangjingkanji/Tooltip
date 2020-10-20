快速创建美观的iOS风格的`Dialog`加载对话框

<img src="https://i.imgur.com/YDvw8Ks.gif" width="250"/>

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
initNet("域名") {
    onDialog { // lambda返回一个Dialog对象
        ProgressDialog(it).apply { // it 为 FragmentActivity
            setMessage("正在努力请求中")
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

