import axios from 'axios';
import Helper from './Helper';
import {ref} from "vue";

const PRODUCT_API_BASE_URL = Helper.getHost() + '/api/items';

export const shelfData = ref({
    shelfItems: []
});

class ShelfItemService {

    getAll() {
        return axios.get(PRODUCT_API_BASE_URL + "/all");
    }

    add(shelfItem) {
        return axios.post(PRODUCT_API_BASE_URL + "/add", shelfItem);
    }

    edit(shelfItem) {
        return axios.post(PRODUCT_API_BASE_URL + "/edit", shelfItem);
    }

    delete(id) {
        return axios.delete(PRODUCT_API_BASE_URL + "/delete", { data: id});
    }

    processMessage(message) {
        shelfData.value.shelfItems = Object.values(message);
    }

    getCategories() {
        let categories = new Set();
        shelfData.value.shelfItems.forEach(item => {
            categories.add(item.category);
        });
        return categories;
    }

}

export default new ShelfItemService();