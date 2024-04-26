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

    getById(id) {
        return axios.get(PRODUCT_API_BASE_URL + "/all", id);
    }

    processMessage(message) {
        shelfData.value.shelfItems = Object.values(message);
    }

}

export default new ShelfItemService();