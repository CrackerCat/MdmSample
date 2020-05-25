[![](https://jitpack.io/v/SpeedataApp/MdmSample.svg)](https://jitpack.io/#SpeedataApp/MdmSample)

1.在项目的build.gradle中添加以下内容
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在modle的依赖中添加以下内容,Tag替换为版本号，上面JitPack标签后面字符

```
dependencies {
		implementation 'com.github.SpeedataApp:MdmSample:Tag'
	}
```


2.调用方法样例，例如重启
```
MdmManager.getInstance().rebootDevice()
```


3.更多api可参考项目下`mdm接口文档`，或者直接查看`MdmManager`类


4.使用须知:

api依赖应用`小拓之家`，所以要保证接口可用则需要确保调用接口前该应用已被安装运行
