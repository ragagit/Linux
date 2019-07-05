import { sortBy } from 'lodash';

// default parameters to fetch data from the api
const DEFAULT_QUERY = 'react';
// const PATH_BASE = 'https://hnalgolia.com/api/v1';
const PATH_BASE = 'https://api.randomuser.me';
const PARAM_SEARCH = '/?nat=US&results=5';


const SORTS = {
  NONE: list => list,
  LNAME: list => sortBy(list, 'name.last'),
  EMAIL: list => sortBy(list, 'email'),
}
export { PATH_BASE, PARAM_SEARCH, SORTS };

