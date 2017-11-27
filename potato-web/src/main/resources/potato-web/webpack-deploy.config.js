'use strict';

var path = require('path');
var webpack = require('webpack');

var thirdParties = ['react',
   'react-bootstrap',
   'react-fontawesome',
   'axios',
   'react-websocket'
];

var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');



const CleanWebpackPlugin = require('clean-webpack-plugin');



var isDevServer = path.basename(require.main.filename) === 'webpack-dev-server.js';


var BUILD_DIR = path.resolve(__dirname,
   '../static');


// the path(s) that should be cleaned
let pathsToClean = [
   'public',
   'index.html',
   'favicon.ico'
]

// the clean options to use
let cleanOptions = {
   root: BUILD_DIR,
   exclude: [],
   verbose: true,
   dry: false
}


var clean = new CleanWebpackPlugin(pathsToClean, cleanOptions);
var Uglify = new webpack.optimize.UglifyJsPlugin();
var plugins = [
   new webpack.optimize.CommonsChunkPlugin({
      names: 'vendors',
      filename: 'public/common.js',
      minChunks: Infinity
   }),
   new HtmlWebpackPlugin({
      template: 'template/index.html',
   }),
   new CopyWebpackPlugin(['template/favicon.ico', {
      from: 'template/lib',
      to: 'public/lib'
   }], {})
];
if (!isDevServer) {
   plugins.push(Uglify);
   plugins.push(clean);
}


module.exports = { 
   entry: {
      app: './src/index.jsx',
      admin: './src/admin.jsx',
      vendors: thirdParties,
   },
   output: {
      path: BUILD_DIR,
      filename: 'public/js/[name].js'
   },
   resolve: {
      extensions: ['.js', '.jsx']
   },
   devServer: {
      proxy: {
         "/api": "http://localhost:8100"
      }
   },
   module: {   
      loaders: [{
         test: /\.css$/, // Only .css files
         loader: 'style-loader!css-loader' // Run both loaders
      }, {
         test: /\.(svg)$/i,
         loader: "file-loader?name=public/svg/[name].[ext]"
      }, {
         test: /\.(jpe?g|png|gif)$/i,
         loader: "file-loader?name=public/images/[name].[ext]"
      }, {
         test: /\.(eot|ttf|woff|woff2)$/,
         loader: 'file-loader?name=public/fonts/[name].[ext]'
      }, {
         test: /.jsx?$/,
         loader: 'babel-loader',
         exclude: /node_modules/,
         query: {
            presets: ['es2015', 'react']
         }
      }],
   },
   plugins: plugins
};
