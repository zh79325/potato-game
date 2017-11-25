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
      app: './src/index.jsx',
      admin: './src/admin.jsx',
      vendors: ['react', 'jquery', 'bootstrap', 'reactstrap'],
   },
   output: {
      path: BUILD_DIR,
      filename: 'js/[name].js'
   },
   resolve: {
      extensions: ['.js', '.jsx']
   },
   module: {   
      loaders: [ {
         test: /\.css$/, // Only .css files
         loader: 'style-loader!css-loader' // Run both loaders
      }
      , {
         test: /\.(svg)$/i,
         loader: "file-loader?name=../public/svg/[name].[ext]"
      }, {
         test: /\.(jpe?g|png|gif)$/i,
         loader: "file-loader?name=../public/images/[name].[ext]"
      }, {
         test: /\.(eot|ttf|woff|woff2)$/,
         loader: 'file-loader?name=../public/fonts/[name].[ext]'
      },{
         test: /.jsx?$/,
         loader: 'babel-loader',
         exclude: /node_modules/,
         query: {
            presets: ['es2015', 'react']
         }
      }],
      //  rules: [{
      //     test: require.resolve('jquery'),
      //     use: [{
      //         loader: 'expose-loader',
      //         options: 'jQuery'
      //     },{
      //         loader: 'expose-loader',
      //         options: '$'
      //     }]
      // }] 
   },
   plugins: [
      new CleanWebpackPlugin(pathsToClean, cleanOptions),
      new webpack.optimize.UglifyJsPlugin(),
      new webpack.optimize.CommonsChunkPlugin({
         names: 'vendors',
         filename: 'common.js',
         minChunks: Infinity
      }),
      new webpack.ProvidePlugin({
         $: "jquery",
         jQuery: "jquery"
      })
   ]
};
