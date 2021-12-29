export interface PageData{

  currentPage: number;
  pageSize: number;
  totalPageSize: number;
  pageSizeItems: [];
  itemsSize: number;
  showFirst: boolean;
  showPrevious: boolean;
  showNext: boolean;
  showLast: boolean;
  sort: string;
  order: string;
  currentShowFromEntries: number;
  currentShowToEntries: number;
}
