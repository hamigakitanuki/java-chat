const mix = require('laravel-mix');

mix.setResourceRoot('dist');

// 下記を追加
mix
  .sass('scss/app.scss', 'main/webapp/dist/')
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
  server: {
    baseDir: '.',
    index: 'index.html'
  },
  watchOptions: {
    usePolling: true,
    interval: 500
  },
  proxy: false,
  files: '**/*'
});
