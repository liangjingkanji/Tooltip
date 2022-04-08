## 使用

<img src="https://i.loli.net/2021/08/14/R5Gg8kKBeTft9PL.gif" width="250"/>

初始化(如果你的应用不存在多进程使用本工具可以不初始化)

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ToastConfig.initialize(this)
    }
}
```

使用`toast`函数显示吐司, 在任何位置都可以直接使用
```kotlin
toast("提示")
```

## 显示

### 短时间显示

显示时间较短的吐司, 最常用的

```kotlin
toast(message)
```

### 长时间显示

显示时间稍长的吐司

```kotlin
longToast(message)
```

## 居中显示吐司

在Android12以后吐司被限制, 所以我们需要自定义吐司视图来实现居中

1. 文字吐司设置Gravity无效, 但是允许应用后台显示
1. 自定义吐司下Gravity有效, 但是不允许应用后台显示

```kotlin
ToastConfig.initialize(this, ToastGravityFactory()) // 屏幕居中显示吐司
```

## 构建吐司工厂

我们可以通过`toast`时传入`tag`参数来到构建吐司工厂来细分显示吐司

以下为居中屏幕显示吐司的示例可以来参考

```kotlin
/**
 * 屏幕居中显示吐司
 * @param gravity 对齐方式
 * @param layout 吐司布局
 * @param xOffset x轴偏移量
 * @param yOffset y轴偏移量
 */
open class ToastGravityFactory(
    val gravity: Int = Gravity.CENTER,
    @LayoutRes val layout: Int = R.layout.layout_gravity_toast,
    val xOffset: Int = 0,
    val yOffset: Int = 0,
) : ToastFactory {

    /**
     * 创建吐司
     * @param context Application
     * @param message 吐司内容
     * @param tag 吐司标签
     */
    override fun onCreate(
        context: Application,
        message: CharSequence,
        duration: Int,
        tag: Any?
    ): Toast? {
        val toast = Toast.makeText(context, message, duration)
        val view = View.inflate(context, layout, null)
        view.findViewById<TextView>(android.R.id.message).text = message
        toast.view = view
        toast.setGravity(gravity, xOffset, yOffset)
        return toast
    }
}
```