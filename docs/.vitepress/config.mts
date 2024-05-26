import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "二次元生日提醒文档",
  description: "简单的文档",
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
      { text: 'API', link: '/search-api' }
    ],

    sidebar: [
      {
        text: 'API',
        items: [
          { text: '查询API', link: '/search-api' }
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/qxdn/birthday-reminder' }
    ]
  }
})
