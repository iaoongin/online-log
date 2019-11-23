# 说明
实现类似 linux tail 命令，用于查看服务器中的log文件。

## 项目结构
 -- online-log-html 页面  
 -- online-log-server 服务
 
## 使用配置
```yml
online:
  log:
    logMap:
      nginx-access:                                    #key
        name: nginx access 日志                        #名称
        path: F:\nginx\nginx-1.16.0\logs\access.log    #路径
      nginx-error:                                     #key
        name: nginx error 日志                          #名称
        path: F:\nginx\nginx-1.16.0\logs\error.log     #路径
```

## 效果图
![图片](https://github.com/iaoongin/online-log/raw/master/asserts/img/webview.png)