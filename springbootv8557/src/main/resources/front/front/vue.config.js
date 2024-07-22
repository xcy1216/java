module.exports = {
    lintOnSave: false,
    publicPath: process.env.NODE_ENV === 'development' ? './' : '././',
    outputDir: 'dist',
    devServer: {
        open: true,
        host: 'localhost',
        port: 8081,
        https: false,
        proxy: {
            '/zhiguo': {
                target: 'http://localhost:8080/zhiguo/',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/zhiguo': ''
                }
            }
        }
    }
}