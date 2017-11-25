var path = require('path');
var webpack = require('webpack');

const CleanWebpackPlugin = require('clean-webpack-plugin');


var BUILD_DIR = path.resolve(__dirname,
   '../static/public');

// the path(s) that should be cleaned
let pathsToClean = [
   'dist',
   'build'
]

// the clean options to use
let cleanOptions = {
   root: BUILD_DIR,
   exclude: ['shared.js'],
   verbose: true,
   dry: false
}

module.exports = { 
   entry: {
      app: './src/index.js',
      admin: './src/admin.js',
      vendors: ['react'],
   },
   output: {
      path: BUILD_DIR,
      filename: 'js/[name].js'
   },
   module: {   
      loaders: [{
         test: /.jsx?$/,
         loader: 'babel-loader',
         exclude: /node_modules/,
         query: {
            presets: ['es2015', 'react']
         }
      }, {
         test: /\.css$/, // Only .css files
         loader: 'style-loader!css-loader' // Run both loaders
      }, {
         test: /\.(jpe?g|png|gif|svg)$/i,
         loader: "file-loader?name=../public/images/[name].[ext]"
      }] 
   },
   plugins: [
      new CleanWebpackPlugin(pathsToClean, cleanOptions),
      new webpack.optimize.UglifyJsPlugin(),
      new webpack.optimize.CommonsChunkPlugin('vendors')
   ]
};
