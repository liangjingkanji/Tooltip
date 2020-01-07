# Tooltip

用于支持各种提示工具

暂时只支持

-   Toast



TODO

-   SnackBar
-   iOS风格吐司

## 安装

project 的 build.gradle

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```



module 的 build.gradle

```groovy
implementation 'com.github.liangjingkanji:Tooltip:1.0.4'
```





## Toast

目前最强吐司工具

### 特性

-   异步线程显示吐司

-   连续弹出吐司不会重叠, 会自动取消上个吐司显示

-   Kotlin特性

-   自定义视图

-   全局等级吐司自定义

    



### 显示吐司

```kotlin
// 短时间显示吐司
toast(message)

// 长时间显示吐司
longToast(message)

// 配置Toast
toast {
    setGravity(Gravity.CENTER, 0, 0)
    view = ProgressBar(this@MainActivity)
}
```



### 全局等级吐司

在某些时候可能需要配置一些错误提醒样式的吐司或者正确样式的吐司

<img src="https://tva1.sinaimg.cn/large/006y8mN6gy1g9179i3gqrj30u01qsjsi.jpg" alt="Screenshot_20191117-180640__01" style="zoom: 25%;" />

在Application中配置全局等级吐司的视图样式

```kotlin
class App : Application() {

    companion object {
        const val LEVEL_ERROR = 1
    }

    override fun onCreate() {
        super.onCreate()

        ToastConfig.onLevel { msg, level ->
            when (level) {
                LEVEL_ERROR -> {
                    view = View.inflate(this@App, R.layout.layout_toast_error, null).apply { tv_msg.text = msg }
                }
            }
        }
    }
}
```



使用

```
 toast("这是一条很长的错误消息", App.LEVEL_ERROR)
```



### 全局默认吐司

可以替换掉系统默认的吐司样式, 使用场景, 某些国产系统例如小米吐司默认会加上应用名称这个时候就可以自定义吐司样式就不会存在应用名称

```kotlin
ToastConfig.onToast { context: Context, msg: CharSequence ->

                     view = View.inflate(context, R.layout.layout_toast_error, null)
                     .apply { tv_msg.text = msg }
                    }
```



设置自动当界面隐藏时自动取消吐司

```
ToastConfig.autoCancel = true
```



详情可以看Sample