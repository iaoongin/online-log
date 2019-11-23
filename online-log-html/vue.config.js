const CompressionWebpackPlugin = require('compression-webpack-plugin');
const path = require('path');

const isProduction = process.env.NODE_ENV === 'production';
const isDevelopment = process.env.NODE_ENV === 'development';
const js_cdn = [
  'https://cdnjs.cloudflare.com/ajax/libs/echarts/4.3.0/echarts.min.js',
  'https://cdn.staticfile.org/vue/2.6.10/vue.min.js',
  'https://cdn.staticfile.org/vue-router/3.0.3/vue-router.min.js',
  'https://cdn.staticfile.org/vuex/3.0.1/vuex.min.js',
  'https://cdn.staticfile.org/axios/0.19.0/axios.min.js',
  'https://cdn.staticfile.org/moment.js/2.24.0/moment.min.js',
  'https://cdn.staticfile.org/element-ui/2.12.0/index.js'
]
const css_cdn = [
  'https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css'
]

const cdn = {
  css: css_cdn,
  js: js_cdn
}

const cdn_dev = {
  css: [
    'https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css'
  ]
}

module.exports = {
  publicPath: isProduction ? '/online-log-html/' : '',
  outputDir: 'online-log-html',
  devServer: {
    proxy: {
      "/": {
        target: process.env.VUE_APP_BASE_API,
        ws: true,
        changeOrigin: true
      }
    },
  },

  configureWebpack: config => {
    if (isProduction) {
      // 为生产环境修改配置...

      // 不打包一下插件，通过html 引入
      config.externals = {
        'vue': 'Vue',
        'vue-router': 'VueRouter',
        'vuex': 'Vuex',
        'axios': 'axios',
        'moment': 'moment',
        'echarts': 'echarts',
        'ElementUI': 'ElementUI',
      }

      // 打包生产.gz包
      config.plugins.push(new CompressionWebpackPlugin({
        algorithm: 'gzip',
        test: new RegExp('\\.(' + ['js', 'css'].join('|') + ')$'),
        threshold: 10240,
        minRatio: 0.8
      }))
    }
    if (isDevelopment) {
      // 为开发环境修改配置...
    }
  },

  chainWebpack: config => {
    // build打包才使用CDN
    if (isProduction) {
      config.plugin('html')
        .tap(args => {
          args[0].cdn = cdn;
          return args;
        })
    }
    if (isDevelopment) {
      // 为开发环境修改配置...
      config.plugin('html')
        .tap(args => {
          args[0].cdn = cdn_dev;
          return args;
        })
    }

    // if (process.env.use_analyzer) { // 分析
    //   config
    //     .plugin('webpack-bundle-analyzer')
    //     .use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin)
    // }
  },
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'less',
      patterns: [
        path.resolve(__dirname, './src/styles/index.less')
      ]
    }
  }
}