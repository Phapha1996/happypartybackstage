
var userType = getCookie("userType");
if(userType==3){
  var navs = [{
      "title": "基本信息",
      "icon": "fa-cubes",
      "spread": true,
      "children": [{
        "title": "轮播设置",
        "icon": "&#xe63c;",
        "href": "/admin/play?type=home"
      }, {
        "title": "推荐公告",
        "icon": "&#xe61c;",
        "href": "/admin/recommend"
      },{
        "title": "修改信息",
        "icon": "&#xe61c;",
        "href": "/admin/user/update"
      }]
    }, {
      "title": "用户管理",
      "icon": "&#x1002;",
      "spread": false,
      "children": [{
        "title": "普通用户",
        "icon": "fa-check-square-o",
        "href": "/admin/user"
      }, {
        "title": "商家用户",
        "icon": "&#xe62a;",
        "href": "/admin/business"
      }]
    },
    {
      "title": "商品管理",
      "icon": "fa-cogs",
      "spread": false,
      "children": [{
          "title": "套餐",
          "icon": "&#xe604;",
          "href": "/admin/meal"
        },
        {
          "title": "场地",
          "icon": "fa-table",
          "href": "/admin/site"
        }, {
          "title": "场地布置",
          "icon": "fa-table",
          "href": "/admin/decoration"
        },
        {
          "title": "餐饮服务",
          "icon": "fa-navicon",
          "href": "/admin/service?type=餐饮服务"
        }, {
          "title": "达人服务",
          "icon": "&#xe62a;",
          "href": "/admin/service?type=达人服务"
        }, {
          "title": "包车服务",
          "icon": "&#xe628;",
          "href": "/admin/service?type=包车服务"
        }
      ]
    }, {
      "title": "定制服务",
      "icon": "&#xe62e;",
      "spread": false,
      "children": [{
        "title": "定制服务",
        "icon": "fa-check-square-o",
        "href": "/admin/custom"
      }]
    }, {
      "title": "帖子管理",
      "icon": "&#xe606;",
      "spread": false,
      "children": [{
        "title": "帖子管理",
        "icon": "fa-check-square-o",
        "href": "/admin/topic"
      }]
    }, {
      "title": "评价管理",
      "icon": "&#xe63c;",
      "spread": false,
      "children": [{
        "title": "评价管理",
        "icon": "fa-check-square-o",
        "href": "/admin/discuss"
      }]
    }, {
      "title": "订单管理",
      "icon": "&#xe629;",
      "spread": false,
      "children": [{
        "title": "订单管理",
        "icon": "fa-check-square-o",
        "href": "/admin/order?status=1"
      }]
    }
  ];
}else if(userType==2){
  var navs = [
    {
      "title": "评价管理",
      "icon": "&#xe63c;",
      "spread": false,
      "children": [{
        "title": "评价管理",
        "icon": "fa-check-square-o",
        "href": "/admin/discuss"
      }]
    }, {
      "title": "订单管理",
      "icon": "&#xe629;",
      "spread": false,
      "children": [{
        "title": "订单管理",
        "icon": "fa-check-square-o",
        "href": "/admin/order?status=1"
      }]
    }
  ];
}
