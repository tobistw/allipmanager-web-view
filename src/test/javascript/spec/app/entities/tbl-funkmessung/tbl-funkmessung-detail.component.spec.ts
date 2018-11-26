/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblFunkmessungDetailComponent } from 'app/entities/tbl-funkmessung/tbl-funkmessung-detail.component';
import { TblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

describe('Component Tests', () => {
    describe('TblFunkmessung Management Detail Component', () => {
        let comp: TblFunkmessungDetailComponent;
        let fixture: ComponentFixture<TblFunkmessungDetailComponent>;
        const route = ({ data: of({ tblFunkmessung: new TblFunkmessung(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblFunkmessungDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TblFunkmessungDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblFunkmessungDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tblFunkmessung).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
