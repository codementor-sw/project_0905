import Vue from 'vue';
import VueRouter from 'vue-router';
import List from './List';
import View from './View';
import Add from './Add';

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: '/',
            name: 'List',
            component: List,

        },
        {
            path: '/list',
            name: 'List',
            component: List,

        },
        {
            path: '/view/:id',
            name: 'View',
            component: View,

        },
        {
            path: '/add',
            name: 'Add',
            component: Add,

        }
    ]
});




export default router;