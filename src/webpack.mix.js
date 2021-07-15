const mix = require('laravel-mix');

mix.setResourceRoot('dist');

// 下記を追加
mix
  .sass('scss/app.scss', 'dist/')
  .options({
    processCssUrls: false,
    autoprefixer: {
      options: {
        grid: true,
      }
    },
  });

// 下記を追加
mix.browserSync({
  browser: "google chrome",
  proxy: {
    target: "http://localhost:8080/WebSocket"
  },
  watchOptions: {
    usePolling: true,
    interval: 500
  },
  files: '**/*'
});
