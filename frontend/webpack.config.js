const path = require('path')
const getWebpackConfig = require('@jetbrains/teamcity-api/getWebpackConfig')

module.exports = getWebpackConfig({
    srcPath: path.join(__dirname, './src'),
    outputPath: path.resolve(__dirname, '../locustio-server/src/main/resources/buildServerResources'),
    entry: './src/index.js',
    useFlow: true,
})