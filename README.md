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
implementation 'com.github.liangjingkanji:Tooltip:1.0.1'
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
toast("吐司信息") {
                view.setBackgroundColor(resources.getColor(R.color.colorAccent)) // 修改吐司背景颜色
			    view.findViewById<TextView>(android.R.id.message).typeface = Typeface.DEFAULT_BOLD // 设置粗体字
            }

toast {
    setGravity(Gravity.CENTER, 0, 0)
    ProgressBar(this@MainActivity)
}
```



### 全局等级吐司

在某些时候可能需要配置一些错误提醒样式的吐司或者正确样式的吐司

![Screenshot_20191117-180640__01](https://tva1.sinaimg.cn/large/006y8mN6gy1g9179i3gqrj30u01qsjsi.jpg)

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
                    View.inflate(this@App, R.layout.layout_toast_error, null).apply { tv_msg.text = msg }
                }
                else -> {
                    null // 返回null表示不显示任何内容
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



详情可以看Sample