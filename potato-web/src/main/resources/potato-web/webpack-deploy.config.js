'use strict';

var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');

var path = require('path');
var webpack = require('webpack');

const CleanWebpackPlugin = require('clean-webpack-plugin');


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

module.exports = { 
   entry: {
      app: './src/index.jsx',
      admin: './src/admin.jsx',
      vendors: ['react', 'jquery', 'bootstrap', 'reactstrap'],
   },
   output: {
      path: BUILD_DIR,
      filename: 'public/js/[name].js'
   },
   resolve: {
      extensions: ['.js', '.jsx']
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
   plugins: [
      new CleanWebpackPlugin(pathsToClean, cleanOptions),
      new webpack.optimize.UglifyJsPlugin(),
      new webpack.optimize.CommonsChunkPlugin({
         names: 'vendors',
         filename: 'public/common.js',
         minChunks: Infinity
      }),
      new webpack.ProvidePlugin({
         $: "jquery",
         jQuery: "jquery"
      }),
      new HtmlWebpackPlugin({
         template: 'template/index.html',
      }),
      new CopyWebpackPlugin(['template/favicon.ico'], {})
   ]
};
