var path = require('path');
var webpack = require('webpack');

module.exports = {
  devtool: 'eval',
  entry: './resources/client/index',
  output: {
    path: path.resolve(__dirname, './resources/public/'),
    filename: 'bundle.js'
  },
  module: {
    loaders: [{
      test: /\.js$/,
      loaders: ['babel-loader'],
      exclude: /node_modules/,
      include: __dirname
    }]
  }
};
