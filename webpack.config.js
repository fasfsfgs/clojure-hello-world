'use strict';

var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: './resources/client/app.js',
    output: {
        path: __dirname + '/resources/public',
        filename: 'bundle.js'
    },

    module: {
        loaders: [{
            // JS LOADER
            test: /\.js$/,
            loader: 'babel',
            exclude: /node_modules/
        }]
    },

    plugins: [
      new HtmlWebpackPlugin({
        template: './resources/client/index.html',
        inject: true
      })
    ]
};
