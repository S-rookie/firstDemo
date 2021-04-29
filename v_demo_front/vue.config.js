module.exports = {
    devServer: {  
      proxy: {  
        '/api': {  
        target: 'http://82.157.174.148:8080', // 后台请求地址  
        ws: true,  
        changeOrigin: true,  
        pathRewrite: {  
            '^/api': '' //通过pathRewrite重写地址，将前缀/api转为/  
        }  
      }  
     }  
    } ,
    publicPath: "./",
    productionSourceMap: false
    }