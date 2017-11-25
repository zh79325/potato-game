'use strict';
var path = require('path');
var webpack = require('webpack');

const CleanWebpackPlugin = require('clean-webpack-plugin');


var RESOURCE_DIR = path.resolve(__dirname,
   '../static');

var BUILD_DIR = path.resolve(RESOURCE_DIR,
   'public');

// the path(s) that should be cleaned
let pathsToClean = [
   'public'
]

// the clean options to use
let cleanOptions = {
   root: RESOURCE_DIR,
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
